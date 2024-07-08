S = list(input())
L = len(S)
count = [0] * 26
flag = False
for i in range(L):
    count[ord(S[i]) - ord('a')] += 1
ans = 0
for i in range(25):
    for j in range(i+1, 26):
        ans += count[i] * count[j]
        if count[i] > 1 or count[j] > 1:
            flag = True
if flag:
    ans += 1
print(ans)