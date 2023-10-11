S = list(map(int, input().split()))
S_sort = S.copy()
S_sort.sort()
bad=0
for i in range(8):
    if S==S_sort:
            if S[i]>=675 or S[i]<=100:
                bad+=1
            else:
                for j in range(8):
                    if not S[j]%25==0:
                        bad+=1
    else:
        bad+=1
    
if bad==0:
    print('Yes')
else:
    print('No')