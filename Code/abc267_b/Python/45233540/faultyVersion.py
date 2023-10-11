s = input()

row = [1]*7
if s[0]=='1':
    print('No')
else:
    if s[6]=='0':
        row[0]=0
    if s[3]=='0':
        row[1]=0
    if s[1]=='0' and s[7]=='0':
        row[2]=0
    if s[4]=='0':
        row[3]=0
    if s[2]=='0' and s[8]=='0':
        row[4]=0
    if s[5]=='0':
        row[5]=0
    if s[9]=='0':
        row[6]=0
    f_s, e_s=None, None
    for i in range(7):
        if row[i]==1:
            f_s = i
            break
    for i in reversed(range(7)):
        if row[i]==1:
            e_s = i
            break
    if f_s and e_s and any(row[i]==0 for i in range(f_s, e_s)):
        print('Yes')
    else:
        print('No')