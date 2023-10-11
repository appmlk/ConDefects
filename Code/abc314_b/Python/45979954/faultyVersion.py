N = int(input())
C, A = [], []
for i in range(N):
    C.append(int(input()))
    A.append(list(map(int, input().split())))
X = int(input())

R = []
min = 37

for i in range(N):
    for a in A[i]:
        if a == X:
            if len(A[i]) == min:
                R.append(i+1)
                break
            elif len(A[i]) < min:
                min = len(A[i])
                R = []
                R.append(i+1)
                break
                
print(len(R))
for r in R:
    print(r, end = "")
