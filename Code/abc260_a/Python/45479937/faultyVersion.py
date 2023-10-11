S = input()
for i in range(2):
    if S.count(S[i]) == 1:
        exit(print(S[i]))
print(-1)
