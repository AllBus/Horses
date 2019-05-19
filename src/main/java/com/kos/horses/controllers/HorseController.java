package com.kos.horses.controllers;


import com.kos.horses.problems.horses.HorseProblem;
import com.kos.horses.problems.horses.HorseProblemFactory;
import com.kos.horses.problems.horses.HorseProblemParams;
import com.kos.horses.structures.IProblem;
import com.kos.horses.structures.ISolverResult;
import com.kos.horses.utils.ConvertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.kos.horses.problems.horses.HorseConstants.NO_SOLUTION_VALUE;


@RestController
public class HorseController {
    private static final Logger log = LoggerFactory.getLogger(HorseController.class);

    @GetMapping(value = "/hourse/rest/count", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> count(@RequestParam long width,
                                        @RequestParam long height,
                                        @RequestParam String start,
                                        @RequestParam String end) {

        HorseProblemParams problemParams;

        try {
            problemParams = HorseProblemFactory.build(width, height, start, end);
        } catch (ConvertException | IllegalArgumentException e) {
            return responseBadRequest();
        }

        IProblem problem = new HorseProblem(problemParams);
        ISolverResult solve = problem.solveProblem();

        if (solve.hasSolution())
            return responseOk(solve.getResult());
        else
            return responseOk(NO_SOLUTION_VALUE);
    }


    private ResponseEntity<String> responseOk(long result) {
        return ResponseEntity.ok(String.valueOf(result));
    }

    private ResponseEntity<String> responseBadRequest() {
        return ResponseEntity.badRequest().body("Bad request");
    }
}
