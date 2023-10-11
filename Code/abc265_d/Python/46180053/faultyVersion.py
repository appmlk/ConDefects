# Aの累積和を出すと、Ax がわかれば、あとは　SA[x] + P  SA[x] + P + Q, SA[x] + P + Q + Z 
# の数字が累積和中に出現するか調べればよい　ただそのまま配列を走査すると、O(N)かかり
# X の走査と併せて O(N**2) となってしまうので、累積和の値をset または dict に突っ込んで
# 存在判定するとよい
import numpy

N, P, Q, R = map(int, input().split())
A = list(map(int, input().split()))

SA = numpy.cumsum(A)
sa_set = set()
for sa in SA:
  sa_set.add(sa)
  
for sa in SA:
  if sa + P in sa_set and sa + P + Q in sa_set and sa + P + Q + R in sa_set:
    print("Yes")
    exit()
print("No")