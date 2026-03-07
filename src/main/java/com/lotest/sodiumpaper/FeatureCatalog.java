package com.lotest.sodiumpaper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class FeatureCatalog {

    public static final List<FeatureDefinition> FEATURES;

    static {
        List<FeatureDefinition> features = new ArrayList<FeatureDefinition>();
        features.add(new FeatureDefinition("dynamic-ai", "Disable monster AI when players are far away.", true));
        features.add(new FeatureDefinition("item-merge", "Merge nearby dropped items into fuller stacks.", true));
        features.add(new FeatureDefinition("cleanup-arrows", "Remove old arrows to reduce entity load.", true));
        features.add(new FeatureDefinition("cleanup-exp-orbs", "Remove excess old experience orbs.", true));
        features.add(new FeatureDefinition("limit-animals", "Set a lower animal spawn limit per world.", true));
        features.add(new FeatureDefinition("limit-monsters", "Set a lower monster spawn limit per world.", true));
        features.add(new FeatureDefinition("spawn-animal-interval", "Increase animal spawn interval ticks.", true));
        features.add(new FeatureDefinition("spawn-monster-interval", "Increase monster spawn interval ticks.", true));
        features.add(new FeatureDefinition("gamerule-randomTickSpeed", "Tune randomTickSpeed gamerule.", true));
        features.add(new FeatureDefinition("gamerule-maxEntityCramming", "Tune maxEntityCramming gamerule.", true));
        features.add(new FeatureDefinition("spawn-keep-spawn-loaded", "Control spawn chunk retention in memory.", true));
        features.add(new FeatureDefinition("world-autosave-interval", "Set world auto-save interval ticks.", true));
        features.add(new FeatureDefinition("tick-monitor", "Collect cycle counters and timings.", true));
        features.add(new FeatureDefinition("entity-scan-batch-limit", "Limit entities processed per cycle.", true));
        features.add(new FeatureDefinition("projectile-scan-batch-limit", "Limit projectile checks per cycle.", true));
        features.add(new FeatureDefinition("command-status-extended", "Show extended status metrics in command output.", true));
        features.add(new FeatureDefinition("command-features-pagination", "Enable paginated feature listing command.", true));
        features.add(new FeatureDefinition("stats-track-cycle-ms", "Track optimization cycle runtime in ms.", true));
        features.add(new FeatureDefinition("safety-validate-thresholds", "Clamp unsafe numeric thresholds.", true));
        features.add(new FeatureDefinition("safety-watchdog-friendly", "Keep cycle time under watchdog risk levels.", true));
        features.add(new FeatureDefinition("micro-opt-001", "Micro optimization toggle #1 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-002", "Micro optimization toggle #2 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-003", "Micro optimization toggle #3 for low-cost server tuning presets.", true));
        features.add(new FeatureDefinition("micro-opt-004", "Micro optimization toggle #4 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-005", "Micro optimization toggle #5 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-006", "Micro optimization toggle #6 for low-cost server tuning presets.", true));
        features.add(new FeatureDefinition("micro-opt-007", "Micro optimization toggle #7 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-008", "Micro optimization toggle #8 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-009", "Micro optimization toggle #9 for low-cost server tuning presets.", true));
        features.add(new FeatureDefinition("micro-opt-010", "Micro optimization toggle #10 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-011", "Micro optimization toggle #11 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-012", "Micro optimization toggle #12 for low-cost server tuning presets.", true));
        features.add(new FeatureDefinition("micro-opt-013", "Micro optimization toggle #13 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-014", "Micro optimization toggle #14 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-015", "Micro optimization toggle #15 for low-cost server tuning presets.", true));
        features.add(new FeatureDefinition("micro-opt-016", "Micro optimization toggle #16 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-017", "Micro optimization toggle #17 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-018", "Micro optimization toggle #18 for low-cost server tuning presets.", true));
        features.add(new FeatureDefinition("micro-opt-019", "Micro optimization toggle #19 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-020", "Micro optimization toggle #20 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-021", "Micro optimization toggle #21 for low-cost server tuning presets.", true));
        features.add(new FeatureDefinition("micro-opt-022", "Micro optimization toggle #22 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-023", "Micro optimization toggle #23 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-024", "Micro optimization toggle #24 for low-cost server tuning presets.", true));
        features.add(new FeatureDefinition("micro-opt-025", "Micro optimization toggle #25 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-026", "Micro optimization toggle #26 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-027", "Micro optimization toggle #27 for low-cost server tuning presets.", true));
        features.add(new FeatureDefinition("micro-opt-028", "Micro optimization toggle #28 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-029", "Micro optimization toggle #29 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-030", "Micro optimization toggle #30 for low-cost server tuning presets.", true));
        features.add(new FeatureDefinition("micro-opt-031", "Micro optimization toggle #31 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-032", "Micro optimization toggle #32 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-033", "Micro optimization toggle #33 for low-cost server tuning presets.", true));
        features.add(new FeatureDefinition("micro-opt-034", "Micro optimization toggle #34 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-035", "Micro optimization toggle #35 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-036", "Micro optimization toggle #36 for low-cost server tuning presets.", true));
        features.add(new FeatureDefinition("micro-opt-037", "Micro optimization toggle #37 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-038", "Micro optimization toggle #38 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-039", "Micro optimization toggle #39 for low-cost server tuning presets.", true));
        features.add(new FeatureDefinition("micro-opt-040", "Micro optimization toggle #40 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-041", "Micro optimization toggle #41 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-042", "Micro optimization toggle #42 for low-cost server tuning presets.", true));
        features.add(new FeatureDefinition("micro-opt-043", "Micro optimization toggle #43 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-044", "Micro optimization toggle #44 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-045", "Micro optimization toggle #45 for low-cost server tuning presets.", true));
        features.add(new FeatureDefinition("micro-opt-046", "Micro optimization toggle #46 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-047", "Micro optimization toggle #47 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-048", "Micro optimization toggle #48 for low-cost server tuning presets.", true));
        features.add(new FeatureDefinition("micro-opt-049", "Micro optimization toggle #49 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-050", "Micro optimization toggle #50 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-051", "Micro optimization toggle #51 for low-cost server tuning presets.", true));
        features.add(new FeatureDefinition("micro-opt-052", "Micro optimization toggle #52 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-053", "Micro optimization toggle #53 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-054", "Micro optimization toggle #54 for low-cost server tuning presets.", true));
        features.add(new FeatureDefinition("micro-opt-055", "Micro optimization toggle #55 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-056", "Micro optimization toggle #56 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-057", "Micro optimization toggle #57 for low-cost server tuning presets.", true));
        features.add(new FeatureDefinition("micro-opt-058", "Micro optimization toggle #58 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-059", "Micro optimization toggle #59 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-060", "Micro optimization toggle #60 for low-cost server tuning presets.", true));
        features.add(new FeatureDefinition("micro-opt-061", "Micro optimization toggle #61 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-062", "Micro optimization toggle #62 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-063", "Micro optimization toggle #63 for low-cost server tuning presets.", true));
        features.add(new FeatureDefinition("micro-opt-064", "Micro optimization toggle #64 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-065", "Micro optimization toggle #65 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-066", "Micro optimization toggle #66 for low-cost server tuning presets.", true));
        features.add(new FeatureDefinition("micro-opt-067", "Micro optimization toggle #67 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-068", "Micro optimization toggle #68 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-069", "Micro optimization toggle #69 for low-cost server tuning presets.", true));
        features.add(new FeatureDefinition("micro-opt-070", "Micro optimization toggle #70 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-071", "Micro optimization toggle #71 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-072", "Micro optimization toggle #72 for low-cost server tuning presets.", true));
        features.add(new FeatureDefinition("micro-opt-073", "Micro optimization toggle #73 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-074", "Micro optimization toggle #74 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-075", "Micro optimization toggle #75 for low-cost server tuning presets.", true));
        features.add(new FeatureDefinition("micro-opt-076", "Micro optimization toggle #76 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-077", "Micro optimization toggle #77 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-078", "Micro optimization toggle #78 for low-cost server tuning presets.", true));
        features.add(new FeatureDefinition("micro-opt-079", "Micro optimization toggle #79 for low-cost server tuning presets.", false));
        features.add(new FeatureDefinition("micro-opt-080", "Micro optimization toggle #80 for low-cost server tuning presets.", false));
        FEATURES = Collections.unmodifiableList(features);
    }

    private FeatureCatalog() {
    }
}
