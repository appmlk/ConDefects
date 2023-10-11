S = list(input())
T = list(input())
for i in range(len(T)):
    if i == len(T)-1:
        ans = i+1
        break
    if S[i] != T[i]:
        ans = i+1
        break
print(ans)