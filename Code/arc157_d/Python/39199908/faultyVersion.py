H,W = map(int,input().split())
S = [list(input()) for i in range(H)]
cnth = []
cntw = []
for i in range(H):
    cnt = 0
    for j in range(W):
        if S[i][j]=="Y":
            cnt+=1
    cnth.append(cnt)
for j in range(W):
    cnt=0
    for i in range(H):
        if S[i][j]=="Y":
            cnt+=1
    cntw.append(cnt)
countx = 0
for i in range(H):
    for j in range(W):
        if S[i][j]=="Y":
            countx+=1
mod = 998244353
#print(countx)
if countx&1 or countx==0:
    print(0)
    exit()
countx>>=1
divisors = []
for i in range(1,countx+1):
    if i*i>countx:
        break
    if i*i==countx:
        divisors.append(i)
        break
    if countx%i==0:
        divisors.append(i)
        divisors.append(countx//i)
divisors.sort()
ans = 0
for m in divisors:
    cut_point = [0]
    n = countx//m
    i = 0
    pack = 0
    flag = False
    case = 1
    for _ in range(m-1):
        while True:
            pack+=cnth[i]
            i+=1
            if pack>=2*n:
                break
        if pack>2*n:
            flag=True
            break
        case_count=1
        cut_point.append(i)
        while True:
            if cnth[i]==0:
                case_count+=1
                i+=1
            else:
                break
        case*=case_count
        case%=mod
    if flag:
        continue
    groups = [m-1 for _ in range(H)]
    for t in range(m-1):
        for h in range(cut_point[t],cut_point[t+1]):
            groups[h]=t
    j = 0
    for _ in range(n-1):
        groups_count = [0 for i in range(m)]
        while True:
            for i in range(H):
                if S[i][j]=="Y":
                    groups_count[groups[i]]+=1
            j+=1
            #print(m,groups_count)
            if min(groups_count)>=2:
                break
        if max(groups_count)>2:
            flag=True
            break
        #print(groups_count)
        #print(n,j)
        case_count=1
        while True:
            if cntw[j]==0:
                case_count+=1
                j+=1
            else:
                break
        case*=case_count
        case%=mod
    if flag:
        continue
    ans+=case
    ans%=mod
print(ans)
                


    



