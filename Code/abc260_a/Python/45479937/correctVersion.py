S = input()
for i in range(3):
    if S.count(S[i]) == 1:
        exit(print(S[i]))
print(-1)
