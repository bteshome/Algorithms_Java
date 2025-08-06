package com.bteshome.algorithms.dynamicProgramming_;

import java.util.*;

public class DPAlgorithms60 {
    /**
     * https://leetcode.com/problems/smallest-sufficient-team
     * NOTE: the solution below is also correct, but not accepted as it's too slow
     * */
    public static class SmallestSufficientTeam {
        private int[] smallest = null;

        public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
            if (req_skills == null || req_skills.length == 0 || people == null || people.isEmpty())
                return new int[0];

            int mask = (1 << req_skills.length) - 1;
            Map<String, Integer> skillNameToIndex = new HashMap<>();
            List<Integer> candidate = new ArrayList<>();
            Map<Integer, Integer> peopleSkillMasks = new HashMap<>();
            Map<Integer, Map<Integer, Integer>> dp = new HashMap<>();

            for (int i = 0; i < req_skills.length; i++)
                skillNameToIndex.put(req_skills[i], i);

            // preprocess people skill masks
            for (int i = 0; i < people.size(); i++) {
                int personSkillMask = 0;

                for (String skill : people.get(i)) {
                    if (skillNameToIndex.containsKey(skill)) {
                        int skillIndex = skillNameToIndex.get(skill);
                        int skillMask = 1 << skillIndex;
                        personSkillMask = personSkillMask | skillMask;
                    }
                }

                peopleSkillMasks.put(i, personSkillMask);
            }

            smallestSufficientTeam(req_skills, people, 0, mask, candidate, peopleSkillMasks, dp);

            return smallest;
        }

        private void smallestSufficientTeam(String[] req_skills, List<List<String>> people, int personPos, int mask,
                                            List<Integer> candidate, Map<Integer, Integer> peopleSkillMasks,
                                            Map<Integer, Map<Integer, Integer>> dp) {
            if (mask == 0) {
                if (smallest == null || candidate.size() < smallest.length)
                    smallest = candidate.stream().mapToInt(Integer::intValue).toArray();
                return;
            }
            if (personPos == people.size())
                return;
            // pruning
            if (smallest != null && candidate.size() >= smallest.length)
                return;

            if (!dp.containsKey(personPos))
                dp.put(personPos, new HashMap<>());
            if (dp.get(personPos).containsKey(mask) && dp.get(personPos).get(mask) <= candidate.size())
                return;
            dp.get(personPos).put(mask, candidate.size());

            smallestSufficientTeam(req_skills, people, personPos + 1, mask, candidate, peopleSkillMasks, dp);

            int personSkillsMask = peopleSkillMasks.get(personPos);
            int newMask = mask & ~personSkillsMask;

            if (newMask != mask) {
                candidate.add(personPos);
                smallestSufficientTeam(req_skills, people, personPos + 1, newMask, candidate, peopleSkillMasks, dp);
                candidate.remove(candidate.size() - 1);
            }
        }
    }

    public static class SmallestSufficientTeam_Slow {
        private int[] smallest = null;

        public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
            if (req_skills == null || req_skills.length == 0 || people == null || people.isEmpty())
                return new int[0];

            NavigableSet<Integer> candidate = new TreeSet<>();
            Set<String> dp = new HashSet<>();

            smallestSufficientTeam(req_skills, people, 0, candidate, dp);

            return smallest;
        }

        private void smallestSufficientTeam(String[] req_skills, List<List<String>> people, int skillPos, NavigableSet<Integer> candidate, Set<String> dp) {
            if (skillPos == req_skills.length) {
                if (smallest == null || candidate.size() < smallest.length)
                    smallest = Arrays.stream(candidate.toArray()).mapToInt(e -> (int) e).toArray();
                return;
            }

            String key = "%s,%s".formatted(skillPos, candidate.toString());
            if (dp.contains(key))
                return;
            dp.add(key);

            String skill = req_skills[skillPos];

            for (int i : candidate) {
                if (people.get(i).contains(skill)) {
                    smallestSufficientTeam(req_skills, people, skillPos + 1, candidate, dp);
                    return;
                }
            }

            for (int i = 0; i < people.size(); i++) {
                if (people.get(i).contains(skill)) {
                    candidate.add(i);
                    smallestSufficientTeam(req_skills, people, skillPos + 1, candidate, dp);
                    candidate.remove(i);
                }
            }
        }
    }
}