# import io
# import sys

# _INPUT = """\
# 3
# 1 2 1
# 1 1 2

# """
# sys.stdin = io.StringIO(_INPUT)

n = int(input())
A = list(map(lambda x:int(x)-1, input().split()))
B = list(map(lambda x:int(x)-1, input().split()))
A_ = [set() for _ in range(n)]
B_ = [set() for _ in range(n)]
for i in range(n):
  a, b = A[i], B[i]
  A_[a].add(i)
  B_[b].add(i)

max_ = 0
for i in range(n):
  if len(A_[i])!=len(B_[i]):
    print('No')
    exit()
  max_ = max(0, len(A_[i]))

if max_>=2: 
  print('Yes')
  exit()

import sys
sys.setrecursionlimit(10**8)
# print(A_, B_, C)
def f(a, cnt):
  # print(a, cnt)
  global st
  if not A_[a]:
    if cnt%2==0: return False
    return True
  # done1.add(a)
  idx = A_[a].pop()
  done.add(idx)
  return f(B[idx], cnt+1)

done = set()
# done1 = set()
ans = 0
for i in range(n):
  if A[i]!=B[i] and i not in done:
    st = A[i]
    done.add(i)
    if not f(A[i], 0): ans ^= 1
  # print(i, ans)

if ans==1:
  print('No')
else:  
  print('Yes')


