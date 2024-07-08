MOD=10**9+7
n = int(input())
s = input()
ans=1
cnt=0
for i in range(n):
    cnt+=1
    if i==n-1 or s[i]==s[i+1]:
        ans*=-(-cnt//2)
        ans%=MOD
        cnt=0
print(ans%MOD)
