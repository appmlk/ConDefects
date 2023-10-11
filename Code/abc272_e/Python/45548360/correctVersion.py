N, M = map(int, input().split())
A = list(map(int, input().split()))

B: "list[set]" = [set() for _ in range(M+1)]

for i in range(N):
    for k in range(max(1, -(A[i]//(i+1))), min(M, -((A[i]-N)//(i+1))-1)+1):
        B[k].add(A[i]+(i+1)*k)

for i in range(1, M+1):
    C = sorted(B[i])
    for j in range(len(C)):
        if C[j] != j:
            print(j)
            break
    else:
        print(len(C))