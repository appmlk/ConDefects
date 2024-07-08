string = input()
point_list = []
for x in string:
    point_list.append(ord(x))
    
code_point = point_list[0]

for x in point_list:
    if x == code_point:
        continue
    elif x == code_point + 1 or x == code_point + 2:
        code_point = x
    else:
        print('No')
        exit()
        
print('Yes')