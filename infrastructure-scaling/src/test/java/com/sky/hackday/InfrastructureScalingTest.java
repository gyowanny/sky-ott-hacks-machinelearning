package com.sky.hackday;

import com.sky.hackday.infrastructure.Infrastructure;
import com.sky.hackday.recommendations.Recommendations;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class InfrastructureScalingTest {
    private static final String APP = "app";
    private InfrastructureScaling test;

    @Mock
    private Recommendations mockRecommendations;

    @Mock
    private Infrastructure mockInfrastructure;

    @Before
    public void setUp() throws Exception {
        test = new InfrastructureScaling(mockRecommendations, mockInfrastructure);
    }

    @Test
    public void scalesUpInstances() throws Exception {
        given(mockRecommendations.getInstances(APP)).willReturn(5);
        given(mockInfrastructure.getInstances(APP)).willReturn(1);

        test.scale(APP);

        verify(mockInfrastructure).scaleUp(APP, 4);
    }

    @Test
    public void scalesDownInstances() throws Exception {
        given(mockRecommendations.getInstances(APP)).willReturn(1);
        given(mockInfrastructure.getInstances(APP)).willReturn(3);

        test.scale(APP);

        verify(mockInfrastructure).scaleDown(APP, 2);
    }

    @Test
    public void doesNotScale() throws Exception {
        given(mockRecommendations.getInstances(APP)).willReturn(3);
        given(mockInfrastructure.getInstances(APP)).willReturn(3);

        test.scale(APP);

        verify(mockInfrastructure).scaleDown(APP, 0);
    }
}
