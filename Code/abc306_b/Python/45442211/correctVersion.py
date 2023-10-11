A = list(map(int,input().split()))
S = 0

import math
for i in range(64):
    S = S + A[i]*(pow(2,i))
    
print(S)