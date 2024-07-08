from itertools import combinations

N, M = list(map(int, input().split()))
flavors_of_stands = []
for _ in range(N):
    s = int("".join(list(map(lambda x: '1' if x == 'o' else '0', input()))), base=2)
    flavors_of_stands.append(s)
total = 2 ** M - 1
for i in range(1, N+1):
    all_cases = combinations(range(N), i)
    for case in all_cases:
        cur_ans = 0
        for idx in case:
            cur_ans |= flavors_of_stands[idx]
        if cur_ans == total:
            print(len(case))
            exit(0)
