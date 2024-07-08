N = int(input())
P = list(map(int,input().split()))

for i in range(1, N + 1):
  if P[-i] > P[-i - 1]:
    continue
  else:
    num = P[-i - 1]
    a = i
    break

A = P[N - a - 1:] # 変化させる部分
A.sort()
B = []

B.append(A[A.index(num) - 1])  # 変化する数字の一番左
A.pop(A.index(num) - 1)
A.sort(reverse=True)           # Bの後ろは逆順

print(*P[:N - a - 1] + B + A)