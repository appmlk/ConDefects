def check_size(length, side, area):
  
  other = 1 - side
  need = (area + length[other] - 1) // length[other]
  
  return length[side] - need


def rec(length, rem):
  
  for i in range(3):
    if not rem & 1 << i:
      continue
      
    n_rem = rem ^ 1 << i
    
    for side in range(2):
      nxt_length = check_size(length, side, area[i])
     
      if nxt_length == 0 and n_rem:
        continue
      
      if nxt_length >= 0:
        if not n_rem:
          return 1
        
        pre_length = length[side]
        length[side] = nxt_length
        
        if rec(length, n_rem):
          return 1
        
        length[side] = pre_length
      
  return 0
  
  
x, y, *area = map(int, input().split())

print("Yes" if rec([x, y], (1 << 3) - 1) else "No")