S = input()
cnt = []
for s in set(S):
    cnt.append(S.count(s))

ans = "Yes"
for i in range(1,max(cnt)+1):
    if cnt.count(i) != 2:
        ans = "No"
        break
print(ans)