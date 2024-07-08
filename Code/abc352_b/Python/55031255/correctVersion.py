s = input()
s2 = input()
x = 0
l = []
while len(l) != len(s):
    for i in range(len(s)):
        for j in range(x, len(s2)):
            if s[i] == s2[j]:
                l.append(j + 1)
                x = j + 1
                break
print(*l)