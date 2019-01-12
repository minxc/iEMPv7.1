/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.minxc.emp.ui.idm.security;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Introduced new interface for our {@link UserDetailsService}, to fool the Spring proxy stuff, so we can inject it into the {@link CustomPersistentRememberMeServices}.
 * 
 * @author Joram Barrez
 */
public interface CustomUserDetailService {

    UserDetails loadByUserId(final String userId);

    UserDetails loadUserByUsername(final String username);

}