/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.benchmarks.turtle.runtime;

import java.util.ArrayList;
import java.util.List;
import org.drools.benchmarks.turtle.runtime.generator.AdvancedOperators4FactsGenerator;
import org.drools.benchmarks.turtle.runtime.generator.GeneratorConfiguration;
import org.drools.benchmarks.turtle.runtime.generator.PlaceHolder;
import org.kie.api.runtime.KieSession;
import org.openjdk.jmh.annotations.Benchmark;

/**
 * Test the Expert features (operators)
 *  - from
 *  - collect
 *  - accumulate - avg, max, min
 */
public class AdvOperators4ExpertBenchmark extends AbstractSimpleRuntimeBenchmark {

    @Override
    protected void addResources() {
        addClassPathResource("turtle/expert-advanced-operators4-100.drl");
    }

    @Override
    protected void addFactsGenerators() {
        addFactsGenerator(new AdvancedOperators4FactsGenerator(getGeneratorConfiguration()),200000);
    }

    @Benchmark
    public KieSession timeFactsInsertionAndRulesFiring() {
        return insertFactsAndFireAllRules();
    }

    private GeneratorConfiguration getGeneratorConfiguration() {
        final GeneratorConfiguration generatorConfiguration = new GeneratorConfiguration(
                100, 5, 0.5);
        generatorConfiguration.setPlaceHolders(getPlaceholders());
        return generatorConfiguration;
    }

    private List<PlaceHolder> getPlaceholders() {
        final List<PlaceHolder> placeHolders = new ArrayList<>();
        placeHolders.add(new PlaceHolder("number1", 100, 10000));
        return placeHolders;
    }
}