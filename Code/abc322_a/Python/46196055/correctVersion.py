n = input()
s = input()

ret = -1
for i in range(0, len(s)-2):
    if s[i:i + 3] == 'ABC':
        ret = i + 1
        break
print(ret)