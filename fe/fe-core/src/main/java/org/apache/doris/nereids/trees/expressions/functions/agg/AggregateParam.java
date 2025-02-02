// Licensed to the Apache Software Foundation (ASF) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The ASF licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

package org.apache.doris.nereids.trees.expressions.functions.agg;

import org.apache.doris.nereids.trees.plans.AggMode;
import org.apache.doris.nereids.trees.plans.AggPhase;

import java.util.Objects;

/** AggregateParam. */
public class AggregateParam {

    public final AggPhase aggPhase;

    public final AggMode aggMode;

    // TODO remove this flag, and generate it in enforce and cost job
    public boolean needColocateScan;

    /** AggregateParam */
    public AggregateParam(AggPhase aggPhase, AggMode aggMode) {
        this(aggPhase, aggMode, false);
    }

    /** AggregateParam */
    public AggregateParam(AggPhase aggPhase, AggMode aggMode, boolean needColocateScan) {
        this.aggMode = Objects.requireNonNull(aggMode, "aggMode cannot be null");
        this.aggPhase = Objects.requireNonNull(aggPhase, "aggPhase cannot be null");
        this.needColocateScan = needColocateScan;
    }

    public static AggregateParam localResult() {
        return new AggregateParam(AggPhase.LOCAL, AggMode.INPUT_TO_RESULT, true);
    }

    public AggregateParam withAggPhase(AggPhase aggPhase) {
        return new AggregateParam(aggPhase, aggMode, needColocateScan);
    }

    public AggregateParam withAggPhase(AggMode aggMode) {
        return new AggregateParam(aggPhase, aggMode, needColocateScan);
    }

    public AggregateParam withAppPhaseAndAppMode(AggPhase aggPhase, AggMode aggMode) {
        return new AggregateParam(aggPhase, aggMode, needColocateScan);
    }

    public AggregateParam withNeedColocateScan(boolean needColocateScan) {
        return new AggregateParam(aggPhase, aggMode, needColocateScan);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AggregateParam that = (AggregateParam) o;
        return Objects.equals(aggPhase, that.aggPhase)
                && Objects.equals(aggMode, that.aggMode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aggPhase, aggMode);
    }

    @Override
    public String toString() {
        return "AggregateParam{"
                + "aggPhase=" + aggPhase
                + ", aggMode=" + aggMode
                + ", needColocateScan=" + needColocateScan
                + '}';
    }
}
