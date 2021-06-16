package com.mortgert.services;

import com.mortgert.data.models.Problem;
import com.mortgert.data.models.Result;
import com.mortgert.data.repos.ProblemRepository;
import net.openhft.compiler.CompilerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompilerService {


    ProblemRepository problemRepository;

    @Autowired
    public CompilerService(ProblemRepository repository){
        this.problemRepository = repository;
    }

    public Result compileJavaCode(String javaCode, String problemID){
        Problem problem = problemRepository.findByProblemID(problemID);

        String codeToRun = problem.getProblemBeginning()+ "\n" +javaCode+ "\n" + problem.getProblemEnd();
        String className = problem.getClassName();

        try{
            Class<?> aClass = CompilerUtils.CACHED_COMPILER.loadFromJava(className,codeToRun);
            Runnable runner = (Runnable) aClass.newInstance();
            runner.run();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return new Result(true);
    }
}
