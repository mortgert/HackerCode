package com.mortgert.controllers;

import com.mortgert.data.models.Result;
import com.mortgert.data.models.Solution;
import com.mortgert.services.CompilerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CompilerController {

    CompilerService compilerService;

    @Autowired
    public CompilerController(CompilerService compilerService){
        this.compilerService = compilerService;
    }

    @PostMapping(name = "/compile")
    public ResponseEntity<Result> compileCodeForProblem(@RequestBody Solution solution){
        Result result = compilerService.compileJavaCode(solution.getSolutionString(), solution.getProblemID());
        return ResponseEntity.ok(result);
    }
}
