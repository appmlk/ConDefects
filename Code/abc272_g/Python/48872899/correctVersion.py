def factorization(n):
    arr = []
    temp = n
    for i in range(2, int(-(-n**0.5//1))+1):
        if temp%i==0:
            cnt=0
            while temp%i==0:
                cnt+=1
                temp //= i
            arr.append(i)

    if temp!=1:
        arr.append(temp)

    if arr==[]:
        arr.append(n)

    return arr

N = int(input())
n = N // 2 + 1
A = list(map(int,input().split()))
M = -1
for i in range(N):
  d = abs(A[i] - A[(i+1)%N])
  if d == 1:
    continue
  P = factorization(d)
  for p in P:
    if p == 2:
      x = 4
      if d % 4 != 0:
        continue
    else:
      x = p
    r = A[i] % x
    s = 0
    for a in A:
      if a % x == r:
        s += 1
    if s >= n:
      M = x
print(M)