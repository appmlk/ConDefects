
s = input()
s_list = []
for i in range(len(s)):
    if s[i] in [chr(i) for i in range(65, 91)]:
        s_list.append(s[i])
if len(s_list) == 2 and s[0] in [chr(i) for i in range(65, 91)] and s[len(s) - 1] in [chr(i) for i in range(65, 91)]:
    if (s[0] in [chr(i) for i in range(65, 91)]) and (s[len(s) - 1] in [chr(i) for i in range(65, 91)]) and (100000 <= int(s[1:len(s) - 1]) <= 999999) and len(s[1:len(s) - 1]) == 6:
        res = 'Yes'
    else:
        res = 'No'
else:
    res = 'No'

print(res)