# import系 ---

# 入力用 ---
INT = lambda: int(input())
MI = lambda: map(int, input().split())
MI_DEC = lambda: map(lambda x: int(x) - 1, input().split())
LI = lambda: list(map(int, input().split()))
LS = lambda: input().split()

# コード ---
N, M = MI()
A_list = sorted(LI())

ans_cand = []

cnt = A_list[0]

for i in range(1, N):
    if A_list[i] - A_list[i-1] <= 1:
        cnt += A_list[i]
    else:
        ans_cand.append(cnt)
        cnt = A_list[i]

if len(ans_cand) == 0:
    print(0)

else:
    if A_list[N-1] == M-1:
        ans_cand[0] += cnt
    else:
        ans_cand.append(cnt)
    
    print(sum(A_list) - max(ans_cand))
