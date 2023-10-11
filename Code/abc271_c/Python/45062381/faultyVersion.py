n=int(input())
a=list(map(int,input().split()))
st=set()
for i in a:
    st.add(i)
ans=0
while n>=0:
    if ans+1 in st:
        n-=1
        ans+=1
    else:
        if n>1:
            n-=2
            ans+=1
        else:
            print(ans)
            exit()
print(ans) 
    