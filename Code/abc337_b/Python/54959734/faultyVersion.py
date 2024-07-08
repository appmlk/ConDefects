s = list(input())

ex_list = [s[0]]
for i in range(len(s)-1):
    if s[i] != s[i+1]:
        ex_list.append(s[i+1])

word = ''.join(ex_list)

if word in ['ABC', 'A', 'B', 'C', 'AC', 'BC']:
    print('Yes')
else:
    print('No')