p=print;r=range;N=int(input());a=[];b=[0]*3;d=N*(N-1)//6
for i in r(N-1,0,-1):
    for c in r(3):
        if b[c]+i<=d:a.append("RBW"[c]*i);b[c]+=i;break
    else:p("No");exit()
p("Yes");p(*a,sep='\n')