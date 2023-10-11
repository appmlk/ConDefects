from itertools import permutations

N = int(input())
A = list(map(int, input().split()))

res = [[] for _ in range(10)]
for i in range(N):
    res[len(str(A[i]))].append(A[i])

for i in range(10):
    res[i].sort(reverse=True)

res2 = []
for i in range(10):
    for j in range(min(3, len(res[i]))):
        res2.append(res[i][j])
    
res3 = []
for i, j, k in permutations(res2, 3):
    res3.append(int(str(i)+str(j)+str(k)))

res3.sort(reverse=True)
print(res3[0])
    