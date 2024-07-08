N = int(input())
A = [list(map(int, input().split())) for _ in range(2*N-1)]
lst = [True]*(2*N)
ans = 0
c = 0
def dfs(lst, score):
    global ans
    global c
    key = -1
    for i in range(2*N):
        if lst[i]:
            key = i
            lst[key] = False
            break
    if key == -1:
        ans = max(ans, score)
        c += 1
        return 

    for i in range(key+1, 2*N):
        if lst[i]:
            lst[i] = False
            s_tmp = A[key][i-key-1]
            score ^= s_tmp
            dfs(lst, score)
            score ^= s_tmp
            lst[i] = True
    lst[key] = True

dfs(lst, 0)
print(ans)
# print('c:', c)
# print('lst:', lst)
'''
2
5 0 1
5 3
0

'''
