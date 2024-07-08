n = int(input())
s = input()
result = set()
ch = s[0]
length = 1
result.add((ch, length))
for i in range(1, n):
    if s[i-1] == s[i]:
        length += 1
        result.add((ch, length))
    else:
        ch = s[i]
        length = 1
        result.add((ch, length))
print(len(result))