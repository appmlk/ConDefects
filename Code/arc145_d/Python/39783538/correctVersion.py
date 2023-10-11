n,m=map(int,input().split())
S=[]
for i in range(2,2*n+1,2):
    s,tmp=0,1
    for j in range(15):
        if i>>j&1:
            s+=tmp
        tmp*=3
    S.append(s)
x=(m-sum(S))%n
for i in range(x):
    S[i]+=1
diff=(sum(S)-m)
print(*[s-diff//n for s in S])