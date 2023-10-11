N = int(input())
P_list = list(map(int, input().split()))
ans = []
for j in range(1, N):
    i = P_list.index(j) + 1
    if i == N:
        ans.append((N-1, N-3))
        P_list = P_list[:N-3] + [P_list[N-2], P_list[N-1], P_list[N-3]]
        i = N-1
        if P_list == list(range(1, N+1)):
            break
        # print(P_list)
    ans.append((i, j-1))
    a, b = P_list[i-1], P_list[i]
    del P_list[i-1:i+1]
    P_list = P_list[:j-1] + [a, b] + P_list[j-1:]
    # print(P_list)
    if P_list == list(range(1, N+1)):
        break

if P_list == list(range(1, N+1)):
    print("Yes")
    print(len(ans))
    for a in ans:
        print(*a)

else:
    print("No")