n=int(input())
s=input()

right,now,ans=0,0,0
for left in range(n):
	while(right<n and s[right]=='o'):
		now+=1
		right+=1
	ans=max(ans,right-left)
	if right==left:right+=1
	else:now-=1 if s[left]=="o" else 0
print(ans if ans!=0 else -1)
