## https://atcoder.jp/contests/abc324/tasks/abc324_g


class Node:

    def __init__(self, value):
        self.value = value
        self.next = None    
        self.prev = None

    def __repr__(self) -> str:
        return str(self.value)

class LinkedList:

    def __init__(self, maintain_middle=False):
        self.head = None
        self.tail = None
        self.size = 0
        self.value_map = {}
        self.maintain_middle = maintain_middle
        self.middle_value = None

    def append(self, value):
        if self.head is None:
            self.head = Node(value)
            self.tail = self.head
            self.value_map[value] = self.head
            if self.maintain_middle:
                self.middle_value = value
        else:
            node = Node(value)
            node.prev = self.tail
            self.tail.next = node
            self.tail = node
            self.value_map[value] = node
            if self.maintain_middle:
                n = (self.size + 1) // 2
                n1 = (self.size + 2) // 2
                if n != n1:
                    self.middle_value = self.value_map[self.middle_value].next.value
        self.size += 1

    def pop(self):
        if self.size == 0:
            return None
        
        value = self.tail.value
        if self.size == 1:
            self.head = None
            self.tail = None
            self.middle_value = None
        else:            
            prev_node = self.tail.prev
            prev_node.next = None
            self.tail = prev_node
            if self.maintain_middle:
                if self.size % 2 == 1:
                    self.middle_value = self.value_map[self.middle_value].prev.value

        self.size -= 1
        del self.value_map[value]
        return value

    def popleft(self):
        if self.size == 0:
            return None
        
        value = self.head.value
        if self.size == 1:
            self.head = None
            self.tail = None
            self.middle_value = None
        else:            
            next_node = self.head.next
            next_node.prev = None
            self.head = next_node
            if self.maintain_middle:
                if self.size % 2 == 0:
                    self.middle_value = self.value_map[self.middle_value].next.value

        del self.value_map[value]
        self.size -= 1
        return value


    def remove(self, value):
        if value not in self.value_map:
            return False
        node = self.value_map[value]

        if node == self.head:
            self.head = node.next
        if node == self.tail:
            self.tail = node.prev

        if node.prev is not None:
            node.prev.next = node.next
        if node.next is not None:
            node.next.prev = node.prev

        if self.size == 1:
            self.head = None
            self.tail = None
            if self.maintain_middle:
                self.middle_value = None
        else:
            if self.maintain_middle:
                if self.size % 2 == 0:
                    if node.value[1] <= self.middle_value[1]:
                        self.middle_value = self.value_map[self.middle_value].next.value
                else:
                    if node.value[1] >= self.middle_value[1]:
                        self.middle_value = self.value_map[self.middle_value].prev.value
        del self.value_map[value]
        self.size -= 1
        return True
    
def print_linked_list(linked_list):
    node = linked_list.head
    print_array = []
    while node is not None:
        print_array.append(node.value)
        node = node.next
    print(print_array)

def main():
    N = int(input())
    A = list(map(int, input().split()))
    Q = int(input())
    tsx = []
    for _ in range(Q):
        t, s, x = map(int, input().split())
        tsx.append((t, s, x))
    

    q_array_list = []
    q_array = [LinkedList(False), LinkedList(True)]
    for i, a in enumerate(A):
        q_array[0].append((i, a))
    a_array = [(i, A[i]) for i in range(N)]
    a_array.sort(key=lambda x: x[1])
    for i, a in a_array:
        q_array[1].append((i, a))
    q_array_list.append(q_array)

    answers = []
    for t, s, x in tsx:
        if t == 1:
            q_array = q_array_list[s]
            if x <= 0:
                q_array_list[s] = [LinkedList(False), LinkedList(True)]
                q_array_list.append(q_array)
            elif x >= q_array[0].size:
                q_array_list.append([LinkedList(False), LinkedList(True)])
            else:
                another_q_array = [LinkedList(False), LinkedList(True)]
                if x > q_array[0].size // 2:

                    queue = []
                    for _ in reversed(range(x, q_array[0].size)):
                        value = q_array[0].pop()
                        q_array[1].remove(value)
                        queue.append(value)

                    queue.reverse()
                    for value in queue:
                        another_q_array[0].append(value)
                    queue.sort(key=lambda x: x[1])
                    for value in queue:
                        another_q_array[1].append(value)
                    q_array_list.append(another_q_array)
                else:
                    queue = []
                    for _ in range(x):
                        value = q_array[0].popleft()
                        q_array[1].remove(value)
                        queue.append(value)
                    
                    for value in queue:
                        another_q_array[0].append(value)
                    queue.sort(key=lambda x: x[1])
                    for value in queue:
                        another_q_array[1].append(value)
                    q_array_list[s] = another_q_array
                    q_array_list.append(q_array)

        else:
            # t == 2
            q_array = q_array_list[s]
            if q_array[1].size == 0:
                q_array_list.append([LinkedList(False), LinkedList(True)])
            else:
                
                min_value = q_array[1].head.value[1]
                max_value = q_array[1].tail.value[1]
                if x >= max_value:
                    q_array_list.append([LinkedList(False), LinkedList(True)])
                elif x < min_value:
                    q_array_list.append(q_array)
                    q_array_list[s] = [LinkedList(False), LinkedList(True)]
                else:
                    another_q_array = [LinkedList(False), LinkedList(True)]
                    mid_value = q_array[1].middle_value
                    if x >= mid_value[1]:
                        j = q_array[1].tail.value[1]
                        queue = []
                        while j > x:
                            value = q_array[1].pop()
                            q_array[0].remove(value)
                            queue.append(value)
                            j = q_array[1].tail.value[1]
                        
                        queue.reverse()
                        for value in queue:
                            another_q_array[1].append(value)
                        queue.sort(key=lambda x: x[0])
                        for value in queue:
                            another_q_array[0].append(value)
                        q_array_list.append(another_q_array)
                    else:
                        j = q_array[1].head.value[1]
                        queue = []
                        while j <= x:
                            value = q_array[1].popleft()
                            q_array[0].remove(value)
                            queue.append(value)
                            j = q_array[1].head.value[1]
                        
                        for value in queue:
                            another_q_array[1].append(value)
                        queue.sort(key=lambda x: x[0])
                        for value in queue:
                            another_q_array[0].append(value)
                        q_array_list[s] = another_q_array
                        q_array_list.append(q_array)
    
        answers.append(q_array_list[-1][1].size)

    for ans in answers:
        print(ans)                    










    


   
if __name__ == "__main__":
    main()
