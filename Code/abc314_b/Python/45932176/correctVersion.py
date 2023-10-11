N = int(input())

C = []
A = []
kouho = []

for i in range(N):
    c = int(input())
    a = list(map(int, input().split()))
    C.append(c)
    A.append(a)

X = int(input())

min_C = max(C)
for i in range(N):
    if X in A[i]:
        kouho.append(i)
        if min_C > C[i]:
            min_C = C[i]
            
count = 0
ans = []
for i in kouho:
    if C[i] == min_C:
        count += 1
        ans.append(i+1)
print(count)
for i in ans:
    print(i, end=" ")