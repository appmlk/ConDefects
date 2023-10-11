N, K = map(int, input().split())
A = list(map(int, input().split()))

ind = [0] * N
for i in range(N):
    ind[A[i] - 1] = i

def re(l, r):
    if r < N - 1:
        return A[:l] + list(reversed(A[l:r + 1])) + A[r + 1:]
    elif r == N - 1:
        return A[:l] + list(reversed(A[l:r + 1]))
    
count = 0
flag = True
visit = set()
for j in range(N): #j+1桁目をみる
    for i in range(N): #j+1桁目が i になる個数
        if i in visit:
            continue
        if ind[i] == j:
            count += (((N - 2 - j) * (N - j - 1)) // 2)
            count += N
        else:
            count += 1
            if count == K:
                print(*re(j, ind[i]))
                exit()
        #print(count, j + 1, i + 1)
        if count > K:
            count -= (((N - 2 - j) * (N - j - 1)) // 2)
            count -= N
            visit.add(i)
            break