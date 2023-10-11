s = input()
n = len(s)

answer = -1
for i in range(n):
    if s[i] == 'a':
        answer = i

print(answer)
