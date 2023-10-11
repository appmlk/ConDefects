N = int(input())
S = input()

ox = 0
ans = -1
cnt = 0
for s in S:
	if s=="-":
		ox |= 0b01
		ans = max(ans, cnt)
		cnt = 0
	elif s=="o":
		ox |= 0b10
		cnt += 1
if ox ==0b11:
	ans = max(ans, cnt)
else:
	ans = -1
print(ans)
