s = input()
r = s[::-1]
if s.find('B') % 2 == r.find('B') % 2:
    if s.find('R') < s.find('K') < 8 - r.find('R') + 1:
        print('Yes')
        exit()
print('No')
