S=input()
T=input()

lis=[]
lit=[]

i=0
kari="A"
cnt=0
while i<=len(S)-1:
    if i==0:
        kari=S[i]
        cnt+=1
    else:
        if S[i-1]==S[i]:
            cnt+=1
        else:
            lis.append((kari,cnt))
            kari=S[i]
            cnt=1
    i+=1
lis.append((kari,cnt))



i=0
kari="A"
cnt=0
while i<=len(T)-1:
    if i==0:
        kari=T[i]
        cnt+=1
    else:
        if T[i-1]==T[i]:
            cnt+=1
        else:
            lit.append((kari,cnt))
            kari=T[i]
            cnt=1
    i+=1
lit.append((kari,cnt))


if len(lis)!=len(lit):
    print("No")
    exit()

for i in range(len(lis)):
    if lis[i][0]!=lit[i][0]:
        print("No")
        exit()
    else:
        if lis[i][1]!=lit[i][1]:
            if lis[i][1]==1 or lis[i][1]>lit[i][1]:
                print("No")
                exit()

print("Yes")
