
s = input()


t = s.rstrip('a')
suffix = len(s) - len(t)


u = t.lstrip('a')
prefix = len(t) - len(u)


if prefix <= suffix:
     print('Yes')
else:
     print('No')