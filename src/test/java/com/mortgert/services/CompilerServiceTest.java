package com.mortgert.services;


import com.mortgert.data.models.Problem;
import com.mortgert.data.repos.ProblemRepository;
import net.openhft.compiler.CompilerUtils;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.junit.Assert.*;


/**
 * CompilerService Tester.
 *
 * @author Nicholas Recino
 * @version 1.0
 * @since Jun 16, 2021
 */
public class CompilerServiceTest {

    @InjectMocks
    CompilerService sut;

    @Mock
    ProblemRepository mockRepo;

    @Before
    public void before() throws Exception {
        openMocks(this);
    }

    @After
    public void after() throws Exception {
        sut = null;
        mockRepo = null;
    }


    @Test
    public void testCompileJavaCode() throws Exception {
        Problem problem = new Problem();
        problem.setProblemBeginning("package mypackage;\n" +
                "public class HelloWorld implements Runnable {\n" +
                "    public void run() {\n");
        problem.setProblemEnd("\n" +
                    "    }\n" +
                    "}\n");
        problem.setClassName("mypackage.HelloWorld");
        problem.setProblemID(1);
        when(mockRepo.findByProblemID(any())).thenReturn(problem);
        assertTrue(sut.compileJavaCode("System.out.println(\"HelloWorld\");","1").isSuccessful());

    }


} 
