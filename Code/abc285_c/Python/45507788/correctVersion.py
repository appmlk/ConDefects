#N=int(input())
S=input()

ans=0

for i in range(len(S)-1,-1,-1):
    x=ord(S[i])-ord('A')+1
    #print(x)

    ans+=26**(len(S)-i-1)*x
    

#a,b=map(int,input().split())
print(ans)


