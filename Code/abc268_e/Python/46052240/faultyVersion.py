N = int(input())
P = list(map(int, input().split()))

cur_cost = 0
cur_slope = 0
slope_diff = [0]*N

for i in range(N):
    # 料理 P[i] は位置 P[i] にあってほしいが，現在位置 i にある
    cur_cost += min((P[i] - i) % N, (i - P[i]) % N)

    if N % 2 == 1:
        if 0 < (P[i] - i) % N <= N//2:
            cur_slope -= 1
        elif (P[i] - i) % N == N//2 + 1:
            cur_slope = 0
        else:
            cur_slope += 1
        slope_diff[(P[i] - i) % N] += 2
        slope_diff[(P[i] - i + N//2) % N] -= 1
        slope_diff[(P[i] - i + N//2 + 1) % N] -= 1
    else:
        if 0 < (P[i] - i) % N <= N//2:
            cur_slope -= 1
        else:
            cur_slope += 1
        slope_diff[(P[i] - i) % N] += 2
        slope_diff[(P[i] - i + N//2) % N] -= 2
    
    # print(i, cur_slope, slope_diff)

ans = cur_cost
# print("slope diff", slope_diff)
# print(0, ans, cur_cost, cur_slope)
for i in range(1, N):
    cur_cost += cur_slope
    ans = min(ans, cur_cost)
    cur_slope += slope_diff[i]
    # print(i, ans, cur_cost, cur_slope)

print(ans)