# import sys
# sys.setrecursionlimit(10**6)
# sys.set_int_max_str_digits(10**6)

# mod = 998244353
# ds = [(-1,0),(0,1),(1,0),(0,-1)]
# alph = 'abcdefghijklmnopqrstuvwxyz'
def rint():
    return list(map(int, input().split())) 

# S = input()
N, = rint()
A = rint()

def sub(A):
    ret = [0]*N
    height = 0
    for i in range(len(A)):
        height += 1
        if A[i] < height:
            height = A[i]
        ret[i] = height
    return ret
L = sub(A) 
R = sub(A[::-1])[::-1]
ans = 1
for i in range(N):
    ans = max(ans, min(L[i], R[i]))

print(ans)