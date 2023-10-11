# https://qiita.com/LorseKudos/items/9eb560494862c8b4eb56
# https://note.com/kai1023/n/naf4e2ef6f88d
# https://techmath-project.com/2023/03/05/abc292/#C_%E2%80%93_Four_Variables

N = int(input())

def make_divisors(i):
    cnt = 0
    for range_num in range(1,int(i **0.5)+1):
        if i % range_num == 0:
            if range_num * range_num < i:
                cnt += 2
            else:
                cnt += 1
    return cnt

le = [make_divisors(i) for i in range(1,N)]

print(le)

ans = 0
for i in range(1, N):
    ans += le[i - 1] * le[N - i - 1]

print(ans)