N=int(input())
a=N%5   

if a==0:
    print(N)
else:
    if a <3:
        ans=N-a
    else:
        ans=N+(5-a)
    print(ans)